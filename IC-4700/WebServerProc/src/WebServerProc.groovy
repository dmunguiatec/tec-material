





enum Method {

    GET('GET'), POST('POST'), PUT('PUT'),
    DELETE('DELETE'), HEAD('HEAD')

    final String value

    Method(String value) {
        this.value = value
    }

    static Method from(String value) { values().find { it.value == value } }
}

class Request {
    Method method
    String path

    Request(String header) {
        println("Received: ${header}")
        def headerParts = header.split(' ')
        this.method = Method.from(headerParts[0])
        this.path = headerParts[1]
    }
}

class WebServer {

    private boolean continueRunning = true

    def notFound() { 'HTTP/1.1 404 Not Found' }
    def methodNotSupported() { 'HTTP/1.1 501 Not Implemented' }

    def stop() {
        continueRunning = false
    }

    def serve(int port, String wwwroot) {
        def server = new ServerSocket(port)

        def thread = Thread.start {
            while (continueRunning) {
                server.accept { socket ->
                    socket.withStreams { inS, outS ->
                        inS.withReader { reader ->

                            Request request = new Request(reader.readLine())

                            switch (request.method) {
                                case Method.GET:
                                    File file = new File("${wwwroot}${request.path}")
                                    if (file.exists()) {
                                        file.withInputStream { fileBytes ->
                                            outS << fileBytes
                                        }
                                    } else {
                                        outS << notFound()
                                    }
                                    break;
                                case Method.POST:
                                case Method.PUT:
                                case Method.DELETE:
                                case Method.HEAD:
                                    outS << methodNotSupported()
                            }
                        }
                    }
                }
            }
        }

        thread.join()
        server.close()
    }
}

WebServer server = new WebServer()

Runtime.runtime.addShutdownHook {
    println "Shutting down..."
    server.stop()
}

server.serve(8081, '/home/diegomunguia/tmp/wwwroot')
