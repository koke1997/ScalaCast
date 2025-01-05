import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

class VideoStreamingSpec extends AnyFlatSpec with Matchers {

  "startAdaptiveBitrateStreaming" should "start adaptive bitrate streaming successfully" in {
    val videoPath = "path/to/video.mp4"
    val outputPath = "path/to/output"
    val result = Await.result(VideoStreaming.startAdaptiveBitrateStreaming(videoPath, outputPath), 10.seconds)
    result shouldBe (())
  }

  "startSubtitleSupport" should "start subtitle support successfully" in {
    val videoPath = "path/to/video.mp4"
    val subtitlePath = "path/to/subtitle.srt"
    val result = Await.result(VideoStreaming.startSubtitleSupport(videoPath, subtitlePath), 10.seconds)
    result shouldBe (())
  }
}
