package io.appium.uiautomator2.server.mjpeg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;

import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.nio.charset.StandardCharsets;
import java.net.Socket;

import static org.mockito.Mockito.spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.appium.uiautomator2.server.mjpeg.MjpegScreenshotServer;
import io.appium.uiautomator2.server.mjpeg.MjpegScreenshotStream;
import io.appium.uiautomator2.server.ServerConfig;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MjpegScreenshotStream.class)
public class MjpegScreenshotTest {
  private static MjpegScreenshotServer serverThread;
  private static int streamingPort;

  @Before
  public void setUp() throws Exception {
    // Create a MJPEG server with a mocked getScreenshot method
    MjpegScreenshotStream mockScreenshotStreamSpy =
        spy(new MjpegScreenshotStream(Collections.emptyList()));
    byte[] mockScreenshotData = "screenshot data".getBytes(StandardCharsets.UTF_8);
    PowerMockito.stub(PowerMockito.method(MjpegScreenshotStream.class, "getScreenshot"))
        .toReturn(mockScreenshotData);
    PowerMockito.whenNew(MjpegScreenshotStream.class)
        .withAnyArguments()
        .thenReturn(mockScreenshotStreamSpy);

    streamingPort = ServerConfig.DEFAULT_MJPEG_SERVER_PORT;
    serverThread = new MjpegScreenshotServer(streamingPort);
    serverThread.start();
    assertTrue(serverThread.isAlive());
  }

  @Test
  public void shouldReceiveDataFromMjpegServer() throws Exception {
    URL url = new URL(String.format("http://localhost:%d", streamingPort));
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    int responseCode = connection.getResponseCode();
    assertEquals(HttpURLConnection.HTTP_OK, responseCode);
  }

  @Test
  public void shouldNotBlockOnUnInitializedClient() throws Exception {
    // Create a socket and connect to the streaming server: it will create an uninitialized client
    Socket socket = new Socket("localhost", streamingPort);
    socket.close();

    // We should still be able to receive data
    URL url = new URL(String.format("http://localhost:%d", streamingPort));
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    int responseCode = connection.getResponseCode();
    assertEquals(HttpURLConnection.HTTP_OK, responseCode);
  }

  @After
  public void tearDown() {
    serverThread.interrupt();
  }
}
