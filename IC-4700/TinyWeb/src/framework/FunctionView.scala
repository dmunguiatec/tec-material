package framework

class FunctionView(viewRenderer: (Map[String, List[String]]) => String) extends View {
  override def render(model: Map[String, List[String]]): String = {
    try {
      viewRenderer(model)
    } catch {
      case e: Exception => throw new RenderingException(e)
    }
  }
}
