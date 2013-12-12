package nl.ead.webservice.services;

import nl.ead.webservice.services.IMoviePrinter;
import nl.ead.webservice.services.MoviePrinter;

/**
 * Description for the class MoviePrinterFactory:
 * <p/>
 * Example usage:
 * <p/>
 * <pre>
 *
 * </pre>
 *
 * @author mdkr
 * @version Copyright (c) 2012 HAN University, All rights reserved.
 */
public class MoviePrinterFactory {
    public IMoviePrinter create() {
        return new MoviePrinter();
    }
}
