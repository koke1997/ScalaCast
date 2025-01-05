import unittest
from src.scala.main.scala.video_chunking import VideoChunking

class TestVideoChunking(unittest.TestCase):

    def test_chunk_video(self):
        video_path = "path/to/test_video.mp4"
        chunk_size = 1048576
        result = VideoChunking.chunkVideo(video_path, chunk_size)
        self.assertEqual(len(result), 5)  # Assuming the video is chunked into 5 parts

    def test_get_chunk(self):
        video_path = "path/to/test_video.mp4"
        chunk_index = 1
        result = VideoChunking.getChunk(video_path, chunk_index)
        self.assertEqual(len(result), 1048576)  # Assuming each chunk is 1MB

if __name__ == '__main__':
    unittest.main()
