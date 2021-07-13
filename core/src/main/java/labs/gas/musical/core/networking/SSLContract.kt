package labs.gas.musical.core.networking

import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

interface SSLContract {
    fun sslServerSocketFactory(): Pair<SSLSocketFactory, X509TrustManager>?
}
