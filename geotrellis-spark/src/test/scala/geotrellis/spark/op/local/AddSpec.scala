package geotrellis.spark.op.local
import geotrellis.spark.TestEnvironment
import geotrellis.spark.rdd.RasterHadoopRDD
import org.apache.hadoop.fs.Path
import org.scalatest.matchers.ShouldMatchers
import org.apache.spark.SparkContext
import geotrellis.spark.utils.SparkUtils
import org.scalatest.fixture.FunSpec
import geotrellis.spark.TestEnvironmentFixture
import org.scalatest.Ignore
import geotrellis.spark.RasterRDDMatchers
import geotrellis.spark.testfiles.AllOnes

class AddSpec extends TestEnvironmentFixture with RasterRDDMatchers {

  describe("Add Operation") {
    val allOnes = AllOnes(inputHome, conf)

    it("should add a constant to a raster") { sc =>

      val ones = RasterHadoopRDD.toRasterRDD(allOnes.path, sc)
      
      val twos = ones + 1

      shouldBe(twos, (2, 2, allOnes.tileCount))
    }

    it("should add multiple rasters") { sc =>

      val ones = RasterHadoopRDD.toRasterRDD(allOnes.path, sc)

      val threes = ones + ones + ones 

      shouldBe(threes, (3, 3, allOnes.tileCount))
    }
  }
}