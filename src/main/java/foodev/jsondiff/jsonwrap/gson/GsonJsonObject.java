package foodev.jsondiff.jsonwrap.gson;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import foodev.jsondiff.jsonwrap.JzonElement;
import foodev.jsondiff.jsonwrap.JzonObject;


public class GsonJsonObject extends GsonJsonElement implements JzonObject {

    private final JsonObject wrapped;


    public GsonJsonObject(JsonObject wrapped) {
        super(wrapped);
        this.wrapped = wrapped;
    }


    @Override
    public boolean has(String key) {
        return wrapped.has(key);
    }


    @Override
    public void add(String key, JzonElement prop) {
        wrapped.add(key, (JsonElement) prop.unwrap());
    }


    @Override
    public void addProperty(String key, int prop) {
        wrapped.addProperty(key, prop);
    }


    @Override
    public Collection<? extends Entry<String, JzonElement>> entrySet() {

        Set<Entry<String, JsonElement>> set = wrapped.entrySet();

        HashSet<Entry<String, JzonElement>> jset = new HashSet<Entry<String, JzonElement>>();

        for (final Entry<String, JsonElement> e : set) {

            final JzonElement el = GsonWrapper.wrap(e.getValue());

            jset.add(new Entry<String, JzonElement>() {

                @Override
                public String getKey() {
                    return e.getKey();
                }


                @Override
                public JzonElement getValue() {
                    return el;
                }


                @Override
                public JzonElement setValue(JzonElement value) {
                    throw new UnsupportedOperationException();
                }
            });
        }

        return jset;

    }


    @Override
    public JzonElement get(String key) {
        return GsonWrapper.wrap(wrapped.get(key));
    }


    @Override
    public void remove(String key) {
        wrapped.remove(key);
    }

}
