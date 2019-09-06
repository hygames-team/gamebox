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

package co.hygames.gamebox.exceptions.language;

import co.hygames.gamebox.exceptions.GameBoxRuntimeException;

/**
 * Thrown if a message is missing in the configured language file AND in the default file
 */
public class MissingMessageException extends GameBoxRuntimeException {
    private static final long serialVersionUID = 1L;

    public MissingMessageException(String message) {
        super(message);
    }
}
