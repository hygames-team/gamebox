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

package co.hygames.gamebox.module.settings;

import java.util.HashMap;
import java.util.Map;

public class ModulesSettings {
    public Map<String, ModuleSettings> modules = new HashMap<>();

    public Map<String, ModuleSettings> getModules() {
        return modules;
    }

    public void setModules(Map<String, ModuleSettings> modules) {
        this.modules = modules;
    }

    public static class ModuleSettings {
        public boolean enabled = true;
        public boolean checkForUpdates = true;

        public boolean isEnabled() {
            return enabled;
        }

        public boolean isCheckForUpdates() {
            return checkForUpdates;
        }
    }
}
