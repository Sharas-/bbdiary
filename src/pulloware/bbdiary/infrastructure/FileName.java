package pulloware.bbdiary.infrastructure;

import java.util.regex.Pattern;

/**
 * Created by sharas on 7/7/15.
 */
public class FileName
{
    public static final String RESERVED_CHARS = "\"*/\\\\:<>?|";
    public static final String SUBSTITUTE = "_";
    private String value;
    private static final Pattern pattern = Pattern.compile("[" + RESERVED_CHARS + "]+?");

    public FileName(String val)
    {
        if (val == null || val.trim() == "")
        {
            throw new IllegalArgumentException("File name cannot be empty");
        }
        if (val.startsWith(" "))
        {
            throw new IllegalArgumentException("File name cannot start with space");
        }
        if (pattern.matcher(val).find())
        {
            throw new IllegalArgumentException("File name cannot contain symbols " + RESERVED_CHARS);
        }
        this.value = val;
    }

    public static FileName sanitize(String val)
    {
        String sanitized = pattern.matcher(val.trim()).replaceAll(SUBSTITUTE);
        return new FileName(sanitized);
    }

    @Override
    public String toString()
    {
        return this.value;
    }
}
