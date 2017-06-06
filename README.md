# lazer

JAVA library to write Streaming jobs with minimal effort. Currently it supports only Spark Streaming.

## How it works?

- For each Spark Streaming job, you need to have a beam file in JSON format. For example: `exampleBeams/events.json`

- Then create a JAVA class with main function and pass this file as an argument. For example:
```
src/main/java/io/github/utkarshcmu/examples/JavaDirectKafkaWordCount.java
```

This Spark Streaming example written using lazer reduces effort and the lines of code you need to write to execute a job.

## InputBeams

Currently, lazer supports following InputBeams:

- KafkaInputBeam

## OutputBeams

Currently, lazer supports following OutputBeams:

- KafkaOutputBeam
- SocketOutputBeam
- HttpOutputBeam

## Want to contribute?

- Fork this repo
- Make a PR
- Lets get going!
