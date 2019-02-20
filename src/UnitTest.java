import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {

	@Test
	public void testGetAlbumInfoNotNull() {
		int albumIdToTest=3;
		assertNotNull(PhotoAlbum.getAlbumInfo(Integer.toString(albumIdToTest)));
		
	}
	@Test
	public void testGetAlbumInfoNotSame() {
		int albumIdToTest=3;
		int otherIdtoTest=4;
		assertNotSame(PhotoAlbum.getAlbumInfo(Integer.toString(albumIdToTest)),Integer.toString(otherIdtoTest));
	}

}
