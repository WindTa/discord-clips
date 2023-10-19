import config from '../config.json';
import { createClient } from '@supabase/supabase-js';

const supabaseClient = createClient(config.supabaseUrl, config.supabaseKey);

export default supabaseClient;
