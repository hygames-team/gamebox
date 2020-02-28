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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package co.hygames.gamebox.module.data;

import java.io.Serializable;
import java.util.List;

import co.hygames.gamebox.utilities.versioning.SemanticVersion;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Niklas Eicker
 */
public class VersionData implements Serializable, VersionedModuleData {
    @SerializedName("version")
    @Expose
    private SemanticVersion version;

    @SerializedName("updatedAt")
    @Expose
    private Long updatedAt;

    @SerializedName("dependencies")
    @Expose
    private List<DependencyData> dependencies = null;

    @SerializedName("releaseNotes")
    @Expose
    private List<String> releaseNotes = null;

    private final static long serialVersionUID = -2433806999627043447L;

    public VersionData() {
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public VersionData withUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public List<DependencyData> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<DependencyData> dependencies) {
        this.dependencies = dependencies;
    }

    public VersionData withDependencies(List<DependencyData> dependencies) {
        this.dependencies = dependencies;
        return this;
    }

    public List<String> getReleaseNotes() {
        return releaseNotes;
    }

    public void setReleaseNotes(List<String> releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    public VersionData withReleaseNotes(List<String> releaseNotes) {
        this.releaseNotes = releaseNotes;
        return this;
    }

    public SemanticVersion getVersion() {
        return version;
    }

    public void setVersion(SemanticVersion version) {
        this.version = version;
    }

    public VersionData withVersion(SemanticVersion version) {
        this.version = version;
        return this;
    }
}
