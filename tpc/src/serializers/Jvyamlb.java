package serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.jvyamlb.YAML;

import data.media.MediaContent;

public class Jvyamlb {
	public static void register(TestGroups groups) {
		groups.media.add(JavaBuiltIn.MediaTransformer,
				Jvyamlb.<MediaContent> GenericSerializer());
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
			return YAML.load(in);
		}

		public byte[] serialize(Object data) throws java.io.IOException {
			ByteArrayOutputStream out = outputStream(data);
			YAML.dump(data, out);
			return out.toByteArray();
		}

		public String getName() {
			return "yaml/jvyamlb";
		}
	};
}
