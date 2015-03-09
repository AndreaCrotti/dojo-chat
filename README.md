# dojo-chat

## Endpoints

REGISTER:
- curl http://localhost:3000/register -d "user=something" --header "Content-type:application/x-www-form-urlencoded" -X POST

GET:
- curl http://localhost:3000/messages?user=andrea

SEND:
- curl http://localhost:3000/messages?user=andrea

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2015 FIXME
