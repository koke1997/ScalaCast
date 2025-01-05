import unittest
from peer_discovery import PeerDiscovery

class TestPeerDiscovery(unittest.TestCase):

    def test_start(self):
        result = PeerDiscovery.start()
        self.assertEqual(result, 'ok')

    def test_stop(self):
        result = PeerDiscovery.stop()
        self.assertEqual(result, 'ok')

    def test_handle_message(self):
        result = PeerDiscovery.handleMessage("Test message")
        self.assertEqual(result, 'ok')

    def test_broadcast_message(self):
        result = PeerDiscovery.broadcastMessage("Test message")
        self.assertEqual(result, 'ok')

if __name__ == '__main__':
    unittest.main()
