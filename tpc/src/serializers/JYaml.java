package serializers;

import java.io.ByteArrayOutputStream;

import java.io.OutputStreamWriter;

import java.io.ByteArrayInputStream;

import org.ho.yaml.Yaml;

import data.media.MediaContent;


public class JYaml
{
	public static void register(TestGroups groups)
	{
		groups.media.add(JavaBuiltIn.MediaTransformer, JYaml.<MediaContent>GenericSerializer());
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
	    
//	    private Yaml yaml;
//
//        {
//            yaml = new Yaml();
//	    }
	    
	    
		public Object deserialize(byte[] array) throws Exception
		{
			ByteArrayInputStream in = new ByteArrayInputStream(array);
			return Yaml.load(in);
		}

		public byte[] serialize(Object data) throws java.io.IOException
		{
			ByteArrayOutputStream out = outputStream(data);
			Yaml.dump(data, out);
			return out.toByteArray();
		}

		public String getName()
		{
			return "yaml/jyaml";
		}
	};
}
