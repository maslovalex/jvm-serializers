package serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.jvyaml.YAML;

import data.media.MediaContent;

public class Jvyaml {
	public static void register(TestGroups groups) {
		groups.media.add(JavaBuiltIn.MediaTransformer,
				Jvyaml.<MediaContent> GenericSerializer());
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
			return YAML.load(new InputStreamReader(in,"UTF-8"));
		}

		public byte[] serialize(Object data) throws java.io.IOException {
			ByteArrayOutputStream out = outputStream(data);
			YAML.dump(data, new OutputStreamWriter(out, "UTF-8"));
			return out.toByteArray();
		}

		public String getName() {
			return "yaml/jvyaml";
		}
	};
}
