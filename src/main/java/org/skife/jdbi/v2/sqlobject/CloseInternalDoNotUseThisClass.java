package org.skife.jdbi.v2.sqlobject;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * This is public as we need it to be for some stuff to work. It is an internal api and NOT to be used
 * by users!
 */
public interface CloseInternalDoNotUseThisClass
{
    public void ___jdbi_close___();

    static class CloseHandler implements Handler
    {
        public Object invoke(HandleDing h, Object target, Object[] args, MethodProxy mp)
        {
            h.getHandle().close();
            return null;
        }
    }

    static class Helper
    {
        static Map<Method, Handler> handlers()
        {
            try {
                Map<Method, Handler> h = new HashMap<Method, Handler>();
                h.put(CloseInternalDoNotUseThisClass.class.getMethod("___jdbi_close___"), new CloseHandler());
                return h;
            }
            catch (NoSuchMethodException e) {
                throw new IllegalStateException("someone wonkered up the bytecode", e);
            }
        }
    }

}
