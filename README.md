Introduction

Java API to interact with any ICAP server and get your content validated for virus. It accepts an input stream as input and gets it validated with any ICAP server. It's tested with c-icap server version 0.3.3 and its expected to work uniformly with other ICAP implementations as well.

ICAP Protocol

The Internet Content Adaption Protocol is heavily inspired by HTTP but the use differs on some core aspects. ICAP is normally implemented as an addition to HTTP, where the HTTP request for a web page can be encapsulated and modified before the user gets the content. This way, a content filter like a anti-virus software, can be transparent to the end-user. In this project, it is just used as a file transfer protocol with a feedback from the server about the file's virus-status.

For further details on ICAP protocol kindly refer its RFC 
