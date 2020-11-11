/*****************************************
** File:    Node.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Derrick Galindo, Manuel Trevino
** Date:    11/7/2020
** Section: 502, 501
** E-mail:  derrickg@tamu.edu, manuelraul5@tamu.edu
**
**   This file contains the structure of our Nodes used in the Merkle tree.
 *   Node serves as a superclass to the subclasses TreeFile and InnerNode.
 *   It allows the subclasses to use the createHash function depending on
 *   what type of node is used.
***********************************************/

import java.io.IOException;
import java.nio.ByteBuffer;
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


    //--------------------------------------------------------------
    // Name: createHash
    // PreCondition:  A valid Path to locate a file
    // PostCondition: Returns the hash of the file at the given Path
    //---------------------------------------------------------------

    /* This method initializes the MessageDigest class to access MD5 hashing. The given path
       is then utilized to obtain the files ByteArray for updating MessageDigest. Digest then returns
       the hash computation and which is then converted to a String to in order to be stored in Node's key
       attribute. */
    protected void createHash(Path path) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] fileData = Files.readAllBytes(path);
        md.update(fileData);
        byte[] digest = md.digest();
        StringBuilder fileHash = new StringBuilder();

        for (int i = 0; i < digest.length; i++) {
            fileHash.append(Integer.toHexString(digest[i] & 0xff).toString());
        }
        
        
        this.setKey(fileHash.toString());
    }

    //-------------------------------------------------------------------------------------
    // Name: createHash
    // PreCondition:  A non-null String that is the concatenated hash of two children nodes
    // PostCondition: Returns the hash of childrenHash for the parent node
    //-------------------------------------------------------------------------------------

    protected void createParentHash(long childrenHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES).putLong(childrenHash);
        md.update(byteBuffer.array());
        byte[] digest = md.digest();
        StringBuilder parentHash = new StringBuilder();

        for (int i = 0; i < digest.length; i++) {
            parentHash.append(Integer.toHexString(digest[i] & 0xff).toString());
        }

        this.setKey(parentHash.toString());
    }

    public Node() {
        this.key = null;
    }
    
}