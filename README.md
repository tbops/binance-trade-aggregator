# Binance Trade Aggregator

A small piece of a larger system, used for ingesting trade from binance into candlesticks data,
you may start the project to have a glympse in what the auto-trader system 

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Getting Started

### Prerequisites

Docker, SBT, Idea

### Installation & Usage

To start the project locally you will have to follow the provided steps:

1.  To build the Producer, run the following shell commands:
    ` project producer
    run sbt stage
    copy artifacts from ./producer/target`

2. Build Docker image:
   `docker build -t producer:1.0 .`

3. Test locally:
   `docker-compose up -d`

## Usage

1. Access kafka-ui at  http://localhost:8081/
2. Click Topics -> first option -> messages
3. Select Live Flow on the right
4. You will be able to see the candlesticks flowing

## Contributing

Feel free to contact me or the team

## License

Information on the project's license, including any restrictions or permissions.

## Acknowledgements

I am most grateful to everyone that made this software possible

## Author

Rafael Fakhreev.

#
### 

