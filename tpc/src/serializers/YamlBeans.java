package serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import data.media.MediaContent;


public class YamlBeans
{
	public static void register(TestGroups groups)
	{
		groups.media.add(JavaBuiltIn.MediaTransformer, YamlBeans.<MediaContent>GenericSerializer());
	}

	public static <T> Serializer<T> GenericSerializer()
	{
		@SuppressWarnings("unchecked")
		Serializer<T> s = (Serializer<T>) GenericSerializer;
		return s;
	}

	// ------------------------------------------------------------
	// Serializer (just one)

	public static Serializer<Object> GenericSerializer = new Serializer<Object>()
	{
	    
		public Object deserialize(byte[] array) throws Exception
		{
			ByteArrayInputStream in = new ByteArrayInputStream(array);
			YamlReader yamlReader = new YamlReader(new InputStreamReader(in));
			return yamlReader.read();
		}

		public byte[] serialize(Object data) throws java.io.IOException
		{
			ByteArrayOutputStream out = outputStream(data);
			YamlWriter yamlWriter = new YamlWriter(new OutputStreamWriter(out)); 
			yamlWriter.write(data);
			yamlWriter.close();
			return out.toByteArray();
		}

		public String getName()
		{
			return "yaml/yamlbeans";
		}
	};
}
