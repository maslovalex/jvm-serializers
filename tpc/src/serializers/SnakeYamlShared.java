package serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import org.yaml.snakeyaml.Yaml;

import data.media.MediaContent;

public class SnakeYamlShared {
    public static void register(TestGroups groups) {
        groups.media.add(JavaBuiltIn.MediaTransformer,
                SnakeYamlShared.<MediaContent> GenericSerializer());
    }

    public static <T> Serializer<T> GenericSerializer() {
        @SuppressWarnings("unchecked")
        Serializer<T> s = (Serializer<T>) GenericSerializer;
        return s;
    }

    // ------------------------------------------------------------
    // Serializer (just one)

    public static Serializer<Object> GenericSerializer = new Serializer<Object>() {

        private Yaml yaml;

        {
            yaml = new Yaml();
        }

        public Object deserialize(byte[] array) throws Exception {
            ByteArrayInputStream in = new ByteArrayInputStream(array);
            return yaml.load(in);
        }

        public byte[] serialize(Object data) throws java.io.IOException {
            ByteArrayOutputStream out = outputStream(data);
            yaml.dump(data, new OutputStreamWriter(out, "UTF-8"));
            return out.toByteArray();
        }

        public String getName() {
            return "yaml/snakeyaml-shared";
        }
    };
}
