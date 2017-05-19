package CXF_HelloWorld;

/**
 * Created by ricardo on 5/19/17.
 */
@WebService
public interface HelloWorld {

    String sayHi(@WebParam(name="text") String text);


    /* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
     * support interfaces directly.  Special XmlAdapter classes need to
     * be written to handle them
     */
    String sayHiToUser(User user);


    /* Map passing
     * JAXB also does not support Maps.  It handles Lists great, but Maps are
     * not supported directly.  They also require use of a XmlAdapter to map
     * the maps into beans that JAXB can use.
     */
    @XmlJavaTypeAdapter(IntegerUserMapAdapter.class)
    Map<Integer, User> getUsers();
}