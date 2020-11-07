/*****************************************
** File:    Node.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Derrick Galindo, Manuel Trevino
** Date:    11/7/2020
** Section: 502, 501
** E-mail:  derrickg@tamu.edu, manuelraul5@tamu.edu
**
**   This file contains the main driver program for FinalProject.
*
***********************************************/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Node {
	
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    protected String createHash(Path path) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] fileData = Files.readAllBytes(path);
        md.update(fileData);
        byte[] digest = md.digest();
        StringBuilder fileHash = new StringBuilder();

        for (int i = 0; i < digest.length; i++) {
            fileHash.append(Integer.toHexString(digest[i] & 0xff).toString());
        }

        return fileHash.toString();

    }

    public Node() {
        this.key = null;
    }
    
}