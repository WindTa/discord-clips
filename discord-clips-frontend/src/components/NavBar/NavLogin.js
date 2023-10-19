import { Auth } from '@supabase/auth-ui-react';
import { ThemeSupa } from '@supabase/auth-ui-shared';
import supabaseClient from '../../services/SupabaseClient';

function NavLogin() {

    return (
        <Auth 
            supabaseClient={supabaseClient}
            appearance={{ theme: ThemeSupa }}
            theme="dark"
            providers={["discord"]}
            onlyThirdPartyProviders={true}
        />
    );
}

export default NavLogin;
