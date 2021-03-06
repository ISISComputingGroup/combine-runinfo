package main.kotlin.combine

import mu.KotlinLogging
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import java.util.*

private val logger = KotlinLogging.logger {}

fun main() {
    val props = Properties()
    props[StreamsConfig.APPLICATION_ID_CONFIG] = "combine-runinfo"
    props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
    props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
    props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass

    val builder = StreamsBuilder()
    val instruments = setOf(
        "LARMOR",
        "ALF",
        "DEMO",
        "IMAT",
        "MUONFE",
        "ZOOM",
        "IRIS",
        "IRIS_SETUP",
        "ENGINX_SETUP",
        "HRPD",
        "POLARIS",
        "VESUVIO",
        "ENGINX",
        "MERLIN",
        "RIKENFE",
        "SELAB",
        "EMMA-A",
        "SANDALS",
        "GEM",
        "MAPS",
        "OSIRIS",
        "INES",
        "TOSCA",
        "LOQ",
        "LET",
        "MARI",
        "CRISP",
        "SOFTMAT",
        "SURF",
        "NIMROD",
        "DETMON",
        "EMU"
    )
    for (item in instruments) {
        val topicName = item + "_runInfo"
        val inStream = builder.stream<String, String>(topicName)
        inStream.to("ALL_runInfo")
    }

    val topology = builder.build()
    logger.info { topology.describe() }

    val streams = KafkaStreams(topology, props)
    streams.start()
}

