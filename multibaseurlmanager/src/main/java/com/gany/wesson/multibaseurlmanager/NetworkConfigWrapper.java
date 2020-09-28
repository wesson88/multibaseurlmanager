package com.gany.wesson.multibaseurlmanager;

import androidx.annotation.NonNull;

import okhttp3.HttpUrl;

/**
 * project MultiBaseUrlManager
 * package com.gany.wesson.multibaseurlmanager
 * fileName NetworkConfigWrapper
 *
 * @author GanYu
 * describe TODO
 * @date 2020/9/28 17:42
 */
public class NetworkConfigWrapper {

    public interface Factory {

        String getHostName();

        int getPathSize();
    }

    static class GenerateBaseUrlFactory implements Factory {

        private static GenerateBaseUrlFactory sInstance;

        private String hostName;
        private int pathSize;

        private GenerateBaseUrlFactory(String url) {
            HttpUrl httpUrl = HttpUrl.get(url);
            hostName = httpUrl.host();
            pathSize = httpUrl.pathSize();
        }

        @NonNull
        static GenerateBaseUrlFactory getInstance(String url) {
            if (sInstance == null) {
                sInstance = new GenerateBaseUrlFactory(url);
            }
            return sInstance;
        }

        @Override
        public String getHostName() {
            return hostName;
        }

        @Override
        public int getPathSize() {
            return pathSize;
        }
    }

    private final String mUrl;
    private final Factory mFactory;

    public NetworkConfigWrapper(String url) {
        this(url, GenerateBaseUrlFactory.getInstance(url));
    }

    public NetworkConfigWrapper(String url, Factory factory) {
        this.mUrl = url;
        this.mFactory = factory;
    }
}
