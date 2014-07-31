package org.jzy3d.demos.surface

import org.jzy3d.analysis.AbstractAnalysis
import org.jzy3d.analysis.AnalysisLauncher
import org.jzy3d.chart.factories.AWTChartComponentFactory
import org.jzy3d.colors.Color
import org.jzy3d.colors.ColorMapper
import org.jzy3d.colors.colormaps.ColorMapRainbow
import org.jzy3d.maths.Range
import org.jzy3d.plot3d.builder.Builder
import org.jzy3d.plot3d.builder.Mapper
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid
import org.jzy3d.plot3d.primitives.Shape
import org.jzy3d.plot3d.rendering.canvas.Quality

import scala.math._

class SurfaceDemo extends AbstractAnalysis{

  override def init: Unit = {
    val mapper: Mapper = new Mapper() {
      def f(x: Double, y: Double) = {
    	  val xDash = x * cos(Pi / 4) - y * sin(Pi / 4)
 	      val yDash = x * sin(Pi / 4) + y * cos(Pi / 4)
       	3 * exp(-1 * (pow(xDash / 1, 2) + pow(yDash / 0.5, 2))) * cos(-2 * Pi * (1 * xDash + 0.5 * yDash) - 0.0) 
      }
    }

    val range: Range = new Range(-3, 3)
    val steps: Int = 80

    val surface: Shape = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper)
    surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), -surface.getBounds().getZmax(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)))
    surface.setFaceDisplayed(true);
    surface.setWireframeDisplayed(false);

    // Create a chart
    chart = AWTChartComponentFactory.chart(Quality.Advanced, getCanvasType())
    chart.getScene().getGraph().add(surface)
	}
}

object SurfaceDemo{
  def main(args: Array[String]) = {
    AnalysisLauncher.open(new SurfaceDemo())
  }
}
