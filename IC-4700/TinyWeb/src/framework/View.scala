package framework

trait View {
  def render(model: Map[String, List[String]]): String
}
