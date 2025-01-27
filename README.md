# sql-police

This repository contains all the code examples from my *JPA Optimizations - SQL Police* talk. 
The already optimized versions can be found on *master* while the yet-to-be-optimized one is on *se-bad-version*. 
You can find a summary of my talk in [my crib](https://originalflipster.com/rants/marie-kondo-sql-queries/)

## Getting started

You can run the code to see log output either via the unit test or by starting the application and use REST endpoints to trigger the respective queries.
In any case, you need to start the Docker container with the database via `docker-compose up` first.