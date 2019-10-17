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

import com.google.zxing.BinaryBitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;

import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.lang.Long;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;


/**
 * This class tests Macro PDF417 barcode encoding/decoding functionality.  It ensures that information can be
 * encoded into several barcodes and can be properly decoded/combined again to yield the original data content.
 *
 * @author Micah Miller
 */
public final class PDF417MacroTestCase {
  @Test
  public void testMacroPdf417Creation() throws WriterException, ReaderException {
    List<Result> results = new ArrayList<Result>();

    int width = 300;
    int height = 100;
    int ec = 5;
    boolean compact = false;
    int margin = 1;

    BufferedImage imageOne = generateImageOne(width, height, ec, compact, margin);
    results.add(decodeImage(imageOne));

    BufferedImage imageTwo = generateImageTwo(width, height, ec, compact, margin);
    results.add(decodeImage(imageTwo));

    int decodedResults = 0;
    int decodedResultWithMetadata = 0;

    for (Result result : results) {
      if (result != null) {
        decodedResults++;
        Map<ResultMetadataType, Object> resultMetaData = result.getResultMetadata();
        if (resultMetaData != null && resultMetaData.get(ResultMetadataType.PDF417_EXTRA_METADATA) != null) {
          PDF417ResultMetadata metadata = (PDF417ResultMetadata)resultMetaData.get(ResultMetadataType.PDF417_EXTRA_METADATA);

          if (metadata != null) {
            decodedResultWithMetadata++;
          }
        }
      }
    }

    assertTrue(decodedResults == 2 && decodedResultWithMetadata == 2);
  }

  private BufferedImage generateImageOne(int width, int height, int ec, boolean compact, int margin) {
    PDF417MacroMetadata macroData = new PDF417MacroMetadata();
    Map<EncodeHintType,Object> hints = new EnumMap<>(EncodeHintType.class);

    hints.put(EncodeHintType.ERROR_CORRECTION, ec);
    hints.put(EncodeHintType.MARGIN, margin);
    hints.put(EncodeHintType.PDF417_COMPACT, compact);

    macroData.setSegmentIndex(0);
    macroData.setSegmentCount(2);
    macroData.setFileId("HELLO.WORLD");
    macroData.setFileName("Bar.code");
    macroData.setSender("From");
    macroData.setAddressee("To");
    macroData.setFileSize(Long.valueOf(9001));
    macroData.setChecksum(300);
    macroData.setTimestamp(new Date().getTime() / 1000);

    hints.put(EncodeHintType.PDF417_MACRO_META_DATA, macroData);

    BitMatrix matrix = new PDF417Writer().encode("Hello", BarcodeFormat.PDF_417, width, height, hints);

    return MatrixToImageWriter.toBufferedImage(matrix);
  }

  private BufferedImage generateImageTwo(int width, int height, int ec, boolean compact, int margin) {
    PDF417MacroMetadata macroData = new PDF417MacroMetadata();
    Map<EncodeHintType,Object> hints = new EnumMap<>(EncodeHintType.class);

    hints.put(EncodeHintType.ERROR_CORRECTION, ec);
    hints.put(EncodeHintType.MARGIN, margin);
    hints.put(EncodeHintType.PDF417_COMPACT, compact);

    macroData.setSegmentIndex(1);
    macroData.setSegmentCount(2);
    macroData.setFileId("HELLO.WORLD");

    hints.put(EncodeHintType.PDF417_MACRO_META_DATA, macroData);

    BitMatrix matrix = new PDF417Writer().encode(" World", BarcodeFormat.PDF_417, width, height, hints);
  }

  private Result decodeImage(BufferedImage image) {
    Result result = null;
    Map<DecodeHintType, Object> decodeHints = new EnumMap<>(DecodeHintType.class);
    List<BarcodeFormat> decoderFormats = new ArrayList<>();
    decoderFormats.add(BarcodeFormat.PDF_417);

    decodeHints.put(DecodeHintType.PURE_BARCODE, true);
    decodeHints.put(DecodeHintType.POSSIBLE_FORMATS, decoderFormats);
    decodeHints.put(DecodeHintType.TRY_HARDER, true);

    PDF417Reader reader = new PDF417Reader();

    LuminanceSource source = new BufferedImageLuminanceSource(image);
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

    Result resultOne = reader.decode(bitmap, decodeHints);

    return result;
  }
}
