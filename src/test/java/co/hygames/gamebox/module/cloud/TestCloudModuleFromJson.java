/*
 * GameBox
 * Copyright (C) 2019  Niklas Eicker
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package co.hygames.gamebox.module.cloud;

import co.hygames.gamebox.module.data.CloudModuleData;
import co.hygames.gamebox.utilities.GameBoxGsonBuilder;
import co.hygames.gamebox.utilities.versioning.SemanticVersion;
import co.hygames.gamebox.module.data.DependencyData;
import co.hygames.gamebox.module.data.VersionData;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Niklas Eicker
 */
public class TestCloudModuleFromJson {
    private static File testCloudModuleFile;
    private static CloudModuleData testCloudModule;

    @BeforeAll
    public static void prepare() {
        testCloudModuleFile = new File("src/test/resources/module/cloud/test_cloud_module.json");
        manuallyBuildTestModule();
    }

    private static void manuallyBuildTestModule() {
        DependencyData dependency = new DependencyData().withId("gamebox").withVersionConstrain("~> 1.0");
        List<DependencyData> dependencies = new ArrayList<>();
        dependencies.add(dependency);
        List<VersionData> versions = new ArrayList<>();
        versions.add(new VersionData()
                .withVersion(new SemanticVersion(1, 0, 0))
                .withDependencies(dependencies)
                .withUpdatedAt(1234L)
                .withReleaseNotes(Arrays.asList(
                        "Changes:",
                        "One change",
                        "A very long change that most probably is over a line long and thus should show what happens if the release notes are written in one long line. This is a second sentence which is also quite long and utterly useless."
                        )
                )
        );
        versions.add(new VersionData()
                .withVersion(new SemanticVersion(1, 1, 0))
                .withDependencies(dependencies)
                .withUpdatedAt(1239L)
                .withReleaseNotes(Arrays.asList(
                        "Some updates..."
                        )
                )
        );
        testCloudModule = new CloudModuleData()
                .withId("test-module")
                .withAuthors(Arrays.asList("Nikl"))
                .withName("Test module")
                .withLastUpdateAt(1239L)
                .withDescription("This module is only for test purposes")
                .withVersions(versions)
                .withLatestVersion(new SemanticVersion(1, 1, 0))
        ;
    }

    @Test
    @DisplayName("correctly parse CloudModuleData from a json file")
    public void parseTestCloudModule() throws FileNotFoundException {
        Gson gson = GameBoxGsonBuilder.build();
        CloudModuleData fileModule = gson.fromJson(new FileReader(testCloudModuleFile), CloudModuleData.class);
        assertAll(
                () -> assertEquals(fileModule.getId(), testCloudModule.getId(),"Not the same id"),
                () -> assertArrayEquals(fileModule.getAuthors().toArray(), testCloudModule.getAuthors().toArray(),"Not the same authors"),
                () -> assertEquals(fileModule.getName(), testCloudModule.getName(),"Not the same name"),
                () -> assertEquals(fileModule.getDescription(), testCloudModule.getDescription(),"Not the same description"),
                () -> assertEquals(fileModule.getVersions().size(), testCloudModule.getVersions().size(),"Not the same number of versions"),
                () -> assertEquals(fileModule.getLatestVersion(), testCloudModule.getLatestVersion(),"Not the same latest version"),
                () -> assertEquals(fileModule.getLastUpdateAt(), testCloudModule.getLastUpdateAt(),"Not the same last updated timestamp")
        );
        Iterator<VersionData> itManualModule = testCloudModule.getVersions().iterator();
        Iterator<VersionData> itFileModule = fileModule.getVersions().iterator();

        while (itManualModule.hasNext() && itFileModule.hasNext()) {
            VersionData version1 = itManualModule.next();
            VersionData version2 = itFileModule.next();
            assertEquals(version1.getVersion(), version2.getVersion(),"Versions: Not the same version");
            assertIterableEquals(version1.getReleaseNotes(), version2.getReleaseNotes(), "Versions: Not the same release notes");
            assertEquals(version1.getUpdatedAt(), version2.getUpdatedAt(), "Versions: Not the same updatedAt timestamp");
            assertEquals(version1.getDependencies().size(), version2.getDependencies().size(),"Versions: Not the same number of dependencies");

            Iterator<DependencyData> itManualDependency = version1.getDependencies().iterator();
            Iterator<DependencyData> itFileDependency = version2.getDependencies().iterator();
            while (itManualDependency.hasNext() && itFileDependency.hasNext()) {
                DependencyData dependency1 = itManualDependency.next();
                DependencyData dependency2 = itFileDependency.next();
                assertEquals(dependency1.getId(), dependency2.getId(),"Dependencies: Not the same id");
                assertEquals(dependency1.getVersionConstrain(), dependency2.getVersionConstrain(),"Dependencies: Not the same version constrain");
            }
        }
    }
}
