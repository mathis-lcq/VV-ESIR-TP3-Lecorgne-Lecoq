package fr.istic.vv;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

  @Test
    public void preparedSocket_NullProtocols() {
        // Mock
        SSLSocket mockSocket = mock(SSLSocket.class);

        // Definition du comportement de getSupportedProtocols et getEnabledProtocols
        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        // Fail si setEnabledProtocols est appelee
        doAnswer(invocation -> {
            fail();
            return null;
        }).when(mockSocket).setEnabledProtocols(any(String[].class));

        // Appel de prepareSocket
        TLSSocketFactory factory = new TLSSocketFactory();
        factory.prepareSocket(mockSocket);
    }

    @Test
    public void typical() {
        // Mock
        SSLSocket mockSocket = mock(SSLSocket.class);

        // Definition du comportement de getSupportedProtocols et getEnabledProtocols
        when(mockSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        // Verification que setEnabledProtocols est appelee avec le tableau attendu
        doAnswer(invocation -> {
            String[] protocols = invocation.getArgument(0);
            assertTrue(Arrays.equals(protocols, new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"}));
            return null;
        }).when(mockSocket).setEnabledProtocols(any(String[].class));

        // Appel de prepareSocket
        TLSSocketFactory factory = new TLSSocketFactory();
        factory.prepareSocket(mockSocket);

        // Verification que setEnabledProtocols a bien ete appelee
        verify(mockSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    // Helper method to shuffle an array
    private String[] shuffle(String[] in) {
        List<String> list = Arrays.asList(in);
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}