import framework.{FunctionController, FunctionView, HttpRequest, TinyWeb}

object TestTinyWeb extends App {

  private def renderGreeting(greeting: String) = {
    "<h2>%s</h2>".format(greeting)
  }

  val greetingView = new FunctionView((model: Map[String, List[String]]) =>
      "<h1>Friendly Greetings: %s</h1>".format((model.getOrElse("greetings", Nil) map renderGreeting) mkString ", "
    )
  )

  val greetingController = new FunctionController(greetingView, { request: HttpRequest =>
    Map("greetings" -> request.body.split(",").toList)
  })

  val filters = List(
    (request: HttpRequest) => {
      println("[INFO] request for path: %s".format(request.path))
      request
    },
    (request: HttpRequest) => {
      HttpRequest(request.headers + ("SESSION_ID" -> "123"), request.body, request.path)
    }
  )

  val tinyweb = new TinyWeb(Map("/greeting" -> greetingController), filters)

  val response = tinyweb.handleRequest(HttpRequest(
    body = "Juan, Pedro, Pablo, Jose",
    path = "/greeting",
    headers = Map[String, String]()
  ))

  Console.println(response)
}



