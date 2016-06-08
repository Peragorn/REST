package com.rsi.rest.auth;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class ServerTrustManager implements X509TrustManager {

  public void checkClientTrusted(X509Certificate[] chain, String authType)
      throws CertificateException {
    // TODO Auto-generated method stub

  }

  public void checkServerTrusted(X509Certificate[] chain, String authType)
      throws CertificateException {
    chain[0].checkValidity();
    chain[0].getIssuerUniqueID();
    chain[0].getSubjectDN();

  }

  public X509Certificate[] getAcceptedIssuers() {
    // TODO Auto-generated method stub
    return null;
  }

}
