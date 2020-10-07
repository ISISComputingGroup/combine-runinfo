# combine-runinfo

Kafka stream processor to combine output of several instrument `_runInfo` topics to an `ALL_runInfo` topic

### Usage

```
gradle clean build
```

Will generate a `.jar` file in `build/libs/` which you can run with `java -jar [filename]`

Before this you should point the broker to whichever broker you want the processor to apply to. 