/*
 * Copyright 2014 Aurélien Gâteau <mail@agateau.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hulab.gettext;

import android.content.Context;

import java.util.Locale;


public class PO {

    private static Messages sMessages = null;

    private static Messages sharedMessages(Context context) {

        if (sMessages == null) {
            String locale = Locale.getDefault().toString();
            setLocal(context, locale);
        }

        return sMessages;
    }

    public static void setLocal(Context context, String locale) {

        try {

            sMessages = Messages.load(context, locale);

        } catch (ClassNotFoundException e) {

            // try with the language iso code
            int idx = locale.indexOf('_');
            if (idx > -1) {
                setLocal(context, locale.substring(0, idx) );
            }

        } catch (Exception e) {
            sMessages = new Messages();
        }
    }

    public static String gettext(Context context, String msgid) {
        return sharedMessages(context).gettext(msgid);
    }

    public static String gettext(Context context, String msgid, Object... args) {
        return String.format(gettext(context, msgid), args);
    }

    public static String xgettext(Context context, String msgid, String msgContext) {
        return sharedMessages(context).xgettext(msgid, msgContext);
    }

    public static String xgettext(String msgid, String context, Object... args) {
        return String.format(xgettext(msgid, context), args);
    }

    public static String ngettext(Context context, String msgid, String msgid_plural, int n) {
        return sharedMessages(context).ngettext(msgid, msgid_plural, n);
    }

    public static String ngettext(String msgid, String msgid_plural, int n, Object... args) {
        return String.format(ngettext(msgid, msgid_plural, n), args);
    }

    public static String nxgettext(Context context, String msgid, String msgid_plural, int n, String msgContext) {
        return sharedMessages(context).nxgettext(msgid, msgid_plural, msgContext, n);
    }

    public static String nxgettext(Context context, String msgid, String msgid_plural, int n, String msgContext, Object... args) {
        return String.format(nxgettext(context, msgid, msgid_plural, n, msgContext), args);
    }

}
