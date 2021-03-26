package com.lyc.spark.core.support;

import java.io.OutputStream;

public interface IMultiOutputStream {

    /**
     * Builds the output stream.
     *
     * @param params the params
     * @return the output stream
     */
    OutputStream buildOutputStream(Integer... params);

}
