package framework

case class HttpRequest(headers: Map[String, String], body: String, path: String)
case class HttpResponse(body: String, responseCode: Int)