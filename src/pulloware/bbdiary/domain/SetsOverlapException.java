package pulloware.bbdiary.domain;

/**
 * Created by sharas on 6/27/15.
 */
public class SetsOverlapException extends IllegalArgumentException
{
    SetsOverlapException(String message)
    {
        super(message);
    }
}
