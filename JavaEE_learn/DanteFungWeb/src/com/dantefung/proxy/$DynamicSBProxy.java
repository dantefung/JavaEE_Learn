/*import com.dantefung.proxy.Human;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class $DynamicSBProxy extends Proxy
  implements Human
{
  private static Method m4;
  private static Method m3;
  private static Method m1;
  private static Method m0;
  private static Method m2;

  public DynamicSBProxy(InvocationHandler paramInvocationHandler)
    throws 
  {
    super(paramInvocationHandler);
  }

  public final void dance(float paramFloat)
    throws 
  {
    try
    {
      this.h.invoke(this, m4, new Object[] { Float.valueOf(paramFloat) });
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Throwable localThrowable)
    {
    }
    throw new UndeclaredThrowableException(localThrowable);
  }

  public final void sing(float paramFloat)
    throws 
  {
    try
    {
      this.h.invoke(this, m3, new Object[] { Float.valueOf(paramFloat) });
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Throwable localThrowable)
    {
    }
    throw new UndeclaredThrowableException(localThrowable);
  }

  public final boolean equals(Object paramObject)
    throws 
  {
    try
    {
      return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Throwable localThrowable)
    {
    }
    throw new UndeclaredThrowableException(localThrowable);
  }

  public final int hashCode()
    throws 
  {
    try
    {
      return ((Integer)this.h.invoke(this, m0, null)).intValue();
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Throwable localThrowable)
    {
    }
    throw new UndeclaredThrowableException(localThrowable);
  }

  public final String toString()
    throws 
  {
    try
    {
      return (String)this.h.invoke(this, m2, null);
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Throwable localThrowable)
    {
    }
    throw new UndeclaredThrowableException(localThrowable);
  }

  static
  {
    try
    {
      m4 = Class.forName("com.dantefung.proxy.Human").getMethod("dance", new Class[] { Float.TYPE });
      m3 = Class.forName("com.dantefung.proxy.Human").getMethod("sing", new Class[] { Float.TYPE });
      m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
      m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
      m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
  }
}*/