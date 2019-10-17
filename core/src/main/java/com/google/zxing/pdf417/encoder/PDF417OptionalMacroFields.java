/*
 * Copyright 2006 Jeremias Maerki in part, and ZXing Authors in part
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file has been modified from its original form in Barcode4J.
 */

package com.google.zxing.pdf417.encoder;

/**
 * Macro PDF417 optional fields
 * The values are set to their field scriptor.
 */
public enum PDF417OptionalMacroFields {
  /**
   * The file name.
   * Field designator: 0
   */
  FileName(0x0),

  /**
   * The segment count field can contain values from 1 to 99,999.
   * Field designator: 1
   */
  SegmentCount(0x1),

  /**
   * The time stamp of the source file expressed in Unix time.
   * Field designator: 2
   */
  TimeStamp(0x2),

  /**
   * The sender.
   * Field designator: 3
   */
  Sender(0x3),

  /**
   * The addressee.
   * Field designator: 4
   */
  Addressee(0x4),

  /**
   * The file size in bytes.
   * Field designator: 5
   */
  FileSize(0x5),

  /**
   * The 16-bit CRC checksum using the CCITT-16 polynomial.
   * Field designator: 6
   */
  Checksum(0x6);


  private int value;

  private PDF417OptionalMacroFields(int value) {
    this.value = value;
  }
}
