import config from '../config.json';

import { createClient } from '@supabase/supabase-js';
import { Auth } from '@supabase/auth-ui-react';
import { ThemeSupa } from '@supabase/auth-ui-shared';

const supabase = createClient(config.supabaseUrl, config.supabaseKey);

function NavAuth() {

    return (
        <Auth 
            supabaseClient={supabase}
            appearance={{ theme: ThemeSupa }}
            theme="dark"
            providers={["discord"]}
            onlyThirdPartyProviders={true}
        />
    );
}

export default NavAuth;
