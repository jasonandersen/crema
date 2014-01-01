package crema.util.text;

import org.apache.commons.lang.Validate;

/**
 * Interprets file size and converts into a human readable string.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class FileSize {

    /**
     * Unit of measure for file size.
     */
    private enum Unit {
        KB(1024, 0, 900000, "%.0f%s"),
        MB(1000 * 1024, 900001, 900000000, "%.0f%s"),
        GB(1000 * 1000 * 1024, 900000001, Long.MAX_VALUE, "%#.1f%s");

        private final long bytesPerUnit;

        private final long minimumThreshold;

        private final long maximumThreshold;

        private final String format;

        /**
         * Constructor.
         * @param bytes
         */
        Unit(final long bytesPerUnit, final long minimumThreshold, final long maximumThreshold, final String format) {
            this.bytesPerUnit = bytesPerUnit;
            this.minimumThreshold = minimumThreshold;
            this.maximumThreshold = maximumThreshold;
            this.format = format;
        }

        /**
         * @param sizeInBytes
         * @return true if the size in bytes is within the threshold for this unit
         */
        public boolean isWithinThreshold(final long sizeInBytes) {
            return sizeInBytes >= minimumThreshold && sizeInBytes <= maximumThreshold;
        }

        /**
         * @return the printf style format to use to display this unit
         */
        public String getFormat() {
            return format;
        }

        /**
         * @param bytesPerUnit
         * @return calculates the number of units in a given amount of bytes
         */
        public double getNumUnits(final long sizeInBytes) {
            return (double) sizeInBytes / (double) bytesPerUnit;
        }

    }

    private final long fileSize;

    /**
     * Constructor.
     */
    public FileSize(final long fileSize) {
        Validate.isTrue(fileSize >= 0, "file size cannot be negative");
        this.fileSize = fileSize;
    }

    /**
     * @return size in bytes
     */
    public long getBytes() {
        return fileSize;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Unit unit = getUnit();
        return String.format(unit.getFormat(), unit.getNumUnits(fileSize), unit);
    }

    /**
     * @return the appropriate unit of measure for this file size
     */
    private Unit getUnit() {
        for (Unit unit : Unit.values()) {
            if (unit.isWithinThreshold(fileSize)) {
                return unit;
            }
        }
        throw new IllegalStateException("Unit of measure not found for " + fileSize);
    }
}
