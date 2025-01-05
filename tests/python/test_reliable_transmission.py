import unittest
from src.scala.main.scala.reliable_transmission import ReliableTransmission

class TestReliableTransmission(unittest.TestCase):

    def test_send_data(self):
        peer = "peer1"
        data = "Test data"
        result = ReliableTransmission.sendData(peer, data)
        self.assertEqual(result, 'ok')

    def test_receive_data(self):
        data_handler = lambda data: self.assertEqual(data, "Test data")
        result = ReliableTransmission.receiveData(data_handler)
        self.assertEqual(result, 'ok')

if __name__ == '__main__':
    unittest.main()
