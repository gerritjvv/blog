---
layout: post
title:  "AES Encryption Keys (password hashing)"
author: gerritjvv
categories: [ encryption, password ]
image: assets/images/aes-encryption-keys-password-hashing.jpg
featured: true
---


# Overview

The prior requirement to any encryption is having a good key.

All known encryption algorithms rely on that the key has a certain length and is non deterministic (indistinguishable from random data). User supplied passwords do not qualify as good encryption keys, they are either too short or not random enough.

Keys must have a minimum length, and the length depends on the encryption algorithm. AES+CBC encryption require a minimum length of 16 bytes (128bits), AES+GCM can work with 12bytes but 16bytes is recommended.


...


<i>This article was published on my medium account, please click on the 
<a target="_blank" href="https://medium.com/@gerritjvv/aes-encryption-keys-password-hashing-6149ee28d9a9?source=friends_link&sk=a2c030b2268041e55dbe6560a22f5a6e">link to read further</a>.</i>
