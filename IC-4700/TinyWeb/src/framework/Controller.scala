package framework

trait Controller {
  def handleRequest(httpRequest: HttpRequest): HttpResponse
}

class FunctionController(view: View,
                         doRequest: (HttpRequest) => Map[String, List[String]])
  extends Controller {

  def handleRequest(request: HttpRequest): HttpResponse = {
    try {
      val model = doRequest(request)
      val responseBody = view.render(model)
      HttpResponse(responseBody, 200)
    } catch {
      case e: RenderingException =>
        HttpResponse("Exception while rendering.", 500)
      case e: Exception =>
        HttpResponse("Internal server error. ", 500)
    }
  }

}
