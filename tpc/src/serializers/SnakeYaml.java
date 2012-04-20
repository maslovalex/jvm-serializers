package serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import org.yaml.snakeyaml.Yaml;

import data.media.MediaContent;

public class SnakeYaml {
	public static void register(TestGroups groups) {
	    groups.media.add(JavaBuiltIn.mediaTransformer, new GenericSerializer<MediaContent>("yaml/snakeyaml", MediaContent.class));
	}

	public static <T> Serializer<T> GenericSerializer() {
		@SuppressWarnings("unchecked")
		Serializer<T> s = (Serializer<T>) GenericSerializer;
		return s;
	}

	// ------------------------------------------------------------
	// Serializer (just one)

	public static Serializer<Object> GenericSerializer = new Serializer<Object>() {

		public Object deserialize(byte[] array) throws Exception {
			ByteArrayInputStream in = new ByteArrayInputStream(array);
			return new Yaml().load(in);
		}

		public byte[] serialize(Object data) throws java.io.IOException {
			ByteArrayOutputStream out = outputStream(data);
			new Yaml().dump(data, new OutputStreamWriter(out, "UTF-8"));
			return out.toByteArray();
		}

		public String getName() {
			return "yaml/snakeyaml";
		}
	};
}
