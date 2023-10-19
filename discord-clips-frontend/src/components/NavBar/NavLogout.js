import { useContext } from 'react';
import UserContext from '../../context/UserContext';

function NavLogout() {
    const { user, signout } = useContext(UserContext);

    return (
        <div className="t-deGdmh">
            <div gap="large" direction="vertical" className="supabase-auth-ui_ui-container c-jTXIoq c-jTXIoq-bsgKCL-direction-vertical c-jTXIoq-bXHFxK-gap-large">
                <div direction="vertical" gap="small" className="supabase-auth-ui_ui-container c-jTXIoq c-jTXIoq-bsgKCL-direction-vertical c-jTXIoq-jjTuOt-gap-small">
                    <button className="supabase-auth-ui_ui-button c-bOcPnF c-bOcPnF-iwjZXY-color-default" onClick={signout} >
                        Sign Out
                    </button>
                </div>
            </div>
        </div>
    );
}

export default NavLogout;
