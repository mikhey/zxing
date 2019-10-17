/*
 * Copyright 2019 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.pdf417;
/**
 * @author Micah Miller
 */
public final class PDF417MacroMetadata {

  private int segmentIndex;
  private String fileId;
  private boolean lastSegment;
  private int segmentCount = -1;
  private String sender;
  private String addressee;
  private String fileName;
  private Long fileSize;
  private Long timestamp;
  private Integer checksum;

  /**
   * The Segment ID represents the segment of the whole file distributed over different symbols.
   *
   * @return File segment index
   */
  public int getSegmentIndex() {
    return segmentIndex;
  }

  public void setSegmentIndex(int segmentIndex) {
    this.segmentIndex = segmentIndex;
  }

  /**
   * Is the same for each related PDF417 symbol
   *
   * @return File ID
   */
  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  /**
   * @return true if it is the last segment
   */
  public boolean isLastSegment() {
    return lastSegment;
  }

  public void setLastSegment(boolean lastSegment) {
    this.lastSegment = lastSegment;
  }

  /**
   * @return count of segments, -1 if not set
   */
  public Integer getSegmentCount() {
    return segmentCount;
  }

  public void setSegmentCount(Integer segmentCount) {
    this.segmentCount = segmentCount;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getAddressee() {
    return addressee;
  }

  public void setAddressee(String addressee) {
    this.addressee = addressee;
  }

  /**
   * Filename of the encoded file
   *
   * @return filename
   */
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * filesize in bytes of the encoded file
   *
   * @return filesize in bytes, -1 if not set
   */
  public Long getFileSize() {
    return fileSize;
  }

  public void setFileSize(Long fileSize) {
    this.fileSize = fileSize;
  }

  /**
   * 16-bit CRC checksum using CCITT-16
   *
   * @return crc checksum, -1 if not set
   */
  public Integer getChecksum() {
    return checksum;
  }

  public void setChecksum(Integer checksum) {
    this.checksum = checksum;
  }

  /**
   * unix epock timestamp, elapsed seconds since 1970-01-01
   *
   * @return elapsed seconds, -1 if not set
   */
  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }
}
