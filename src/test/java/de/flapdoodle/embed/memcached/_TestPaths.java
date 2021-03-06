/**
 * Copyright (C) 2011
 *   Michael Mosmann <michael@mosmann.de>
 *   Martin Jöhren <m.joehren@googlemail.com>
 *
 * with contributions from
 * 	konstantin-ba@github, Archimedes Trajano (trajano@github), Christian Bayer (chrbayer84@googlemail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.flapdoodle.embed.memcached;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.flapdoodle.embed.memcached.distribution.Version;
import de.flapdoodle.embed.process.distribution.BitSize;
import de.flapdoodle.embed.process.distribution.Distribution;
import de.flapdoodle.embed.process.distribution.Platform;

public class _TestPaths {

	private PackagePaths paths;

	@Before
	public void setUp() throws Exception {
		paths = new PackagePaths(Command.MemcacheD);
	}

	@Test
	public void testDistributionPathsLinux() {
		// v 1.4.15_1
		checkPath(new Distribution(Version.V1_4_15, Platform.Linux,
				BitSize.B64),
				"/1.4.15_2/memcached-dist-1.4.15_2-linux.tar.gz");
		checkPath(new Distribution(Version.V1_4_15, Platform.Windows,
				BitSize.B32),
				"/1.4.15_2/memcached-dist-1.4.15_2-windows.zip");
		checkPath(new Distribution(Version.V1_4_15, Platform.OS_X,
				BitSize.B64),
				"/1.4.15_2/memcached-dist-1.4.15_2-macos.tar.gz");
		// v 2.6.14
		// checkPath(
		// new Distribution(Version.V1_4_14, Platform.Linux, BitSize.B64),
		// "/1.4.14_1/memcached-dist-1.4.14_1-linux.tar.gz");
		// checkPath(new Distribution(Version.V1_4_14, Platform.Windows,
		// BitSize.B32), "/1.4.14_1/memcached-dist-1.4.14_1-windows.zip");
		// checkPath(
		// new Distribution(Version.V1_4_14, Platform.OS_X, BitSize.B64),
		// "/1.4.14_1/memcached-dist-1.4.14_1-macos.tar.gz");
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	@Ignore
	public void testDistributionPathsOSX() {
		checkPath(new Distribution(Version.V1_4_15, Platform.OS_X,
				BitSize.B32), " ");
	}

	@Test(expected = IllegalArgumentException.class)
	@Ignore
	public void testDistributionPathsWindows() {
		checkPath(new Distribution(Version.V1_4_15, Platform.Windows,
				BitSize.B64), " ");
	}

	private void checkPath(Distribution distribution, String match) {
		assertEquals("" + distribution, match, paths.getPath(distribution));
	}

	@Test
	public void testPaths() {
		for (Version v : Version.values()) {
			assertNotNull("" + v, PackagePaths.getVersionPart(v));
		}
	}

}
